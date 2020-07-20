package br.com.zup.xyinc.delivery

data class Response<T>(
        val errors: ArrayList<String> = arrayListOf(),
        var data: T? = null
)
