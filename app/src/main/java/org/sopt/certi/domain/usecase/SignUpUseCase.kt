package org.sopt.certi.domain.usecase

import org.sopt.certi.domain.model.user.SignUpResult
import org.sopt.certi.domain.model.user.UserInformationAuth
import org.sopt.certi.domain.repository.AuthRepository

class SignUpUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        preSignupToken: String,
        userInformation: UserInformationAuth?,
        university: String,
        grade: String,
        track: String,
        major: String,
        jobs: List<String>
    ): Result<SignUpResult> =
        authRepository.signUp(
            preSignupToken = preSignupToken,
            userInformation = userInformation,
            university = university,
            grade = grade,
            track = track,
            major = major,
            jobs = jobs
        )
}
