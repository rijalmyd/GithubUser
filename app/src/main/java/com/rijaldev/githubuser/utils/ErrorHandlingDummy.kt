package com.rijaldev.githubuser.utils

import com.rijaldev.githubuser.data.source.remote.response.detail.DetailUserResponse
import com.rijaldev.githubuser.data.source.remote.response.detailrepo.DetailRepoResponse

object ErrorHandlingDummy {
    fun getDetailDummy(): DetailUserResponse = DetailUserResponse(0,
            "",
            0,
            "",
            "",
            "",
            "",
            0,
            0,
            "",
            "",
            ""
    )

    fun getDetailRepoDummy(): DetailRepoResponse = DetailRepoResponse(
            "",
            0,
            0,
            0,
            "",
            "",
            "",
            "",
            0,
            0,
            0,
            0
    )
}