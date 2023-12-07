package com.dicoding.definderapps.model

object InitialDataSource{
    fun getDestination():List<Destination>{
        return listOf(
            Destination(
                id= 1,
                name="Candi Borobudur",
                imageUrl = "candi_borobudur",
                location = "Magelang, Central Java",
                rating = "4.9 (3,214)",
                favorited=false
            ),
            Destination(
                id = 2,
                name = "Candi Prambanan",
                imageUrl = "candi_prambanan",
                location = "Sleman, Yogyakarta",
                rating = "4.8 (2,891)",
                favorited=false
            ),
            Destination(
                id = 3,
                name = "Candi Sewu",
                imageUrl = "candi_sewu",
                location = "Klaten, Central Java",
                rating = "4.7 (1,523)",
                favorited=false
            ),
            Destination(
                id = 4,
                name = "Candi Plaosan",
                imageUrl = "candi_plaosan",
                location = "Klaten, Central Java",
                rating = "4.8 (872)",
                favorited=false
            ),
            Destination(
                id = 5,
                name = "Candi Dieng",
                imageUrl = "candi_dieng",
                location = "Wonosobo, Jawa Tengah",
                rating = "4.7 (942)",
                favorited=false
            ),
            Destination(
                id = 6,
                name = "Candi Gedong Songo",
                imageUrl = "candi_gedong_songo",
                location = "Semarang, Central Java",
                rating = "4.6 (532)",
                favorited=false
            ),
            Destination(
                id = 7,
                name = "Candi Penataran",
                imageUrl = "candi_penataran",
                location = "Blitar, East Java",
                rating = "4.8 (1,231)",
                favorited=false
            ),
            Destination(
                id = 8,
                name = "Candi Kalasan",
                imageUrl = "candi_kalasan",
                location = "Sleman, Yogyakarta",
                rating = "4.8 (624)",
                favorited=false
            ),
            Destination(
                id = 9,
                name = "Pantai Pandawa",
                imageUrl = "pantai_pandawa",
                location = "Kutuh, Bali",
                rating = "4.9 (2,143)",
                favorited=false
            ),
            Destination(
                id = 10,
                name = "Pura Tanah Lot",
                imageUrl = "pura_tanah_lot",
                location = "Tabanan, Bali",
                rating = "4.9 (1,823)",
                favorited=false
            )
        )
    }
}