package com.example.flam.models

class PopularModel {
    var name: String? = null
    var description: String? = null
    var rating: String? = null
    var discount: String? = null
    var type: String? = null
    var img_url: String? = null

    constructor() {}
    constructor(
        name: String?,
        description: String?,
        rating: String?,
        discount: String?,
        type: String?,
        img_url: String?
    ) {
        this.name = name
        this.description = description
        this.rating = rating
        this.discount = discount
        this.type = type
        this.img_url = img_url
    }
}