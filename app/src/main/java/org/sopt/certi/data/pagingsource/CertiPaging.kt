package org.sopt.certi.data.pagingsource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState

class CertiPagingSource<T : Any>(
    private val getList: suspend (Int) -> List<T>,
) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        val anchor = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchor) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val currentPage = params.key ?: 0
        return try {
            val list = getList(currentPage)
            LoadResult.Page(
                data = list,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (list.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

fun <T: Any> createPager(
    limit: Int = 10,
    initialLoadSize: Int = 20,
    q: List<String>? = null,
    startPage: Int? = null,
    pagingSourceFactory: suspend (page: Int, limit: Int, sort: List<String>?) -> List<T>
) : Pager<Int, T> {
    return Pager(
        config = PagingConfig(
            pageSize = limit,
            initialLoadSize = initialLoadSize,
            prefetchDistance = 1,
            enablePlaceholders = false
        ),
        initialKey = startPage ?: 0,
        pagingSourceFactory = {
            CertiPagingSource<T> { page ->
                pagingSourceFactory(page, limit, q)
            }
        }
    )
}