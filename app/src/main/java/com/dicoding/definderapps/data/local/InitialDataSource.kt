package com.dicoding.definderapps.data.local

import com.dicoding.definderapps.data.local.dao.AboutDestination
import com.dicoding.definderapps.data.local.dao.Destination
import com.dicoding.definderapps.data.local.dao.ImageDestination
import kotlin.random.Random

object InitialDataSource{
    fun getDestination():List<Destination>{
        return listOf(
            Destination(
                id= 1,
                name="Candi Borobudur",
                location = "Magelang, Central Java",
                rating = "4.9 (3,214)",
                price = Random.nextFloat() * (10.0f - 0.5f) + 0.5f,
                favorited=false
            ),
            Destination(
                id = 2,
                name = "Candi Prambanan",
                location = "Sleman, Yogyakarta",
                rating = "4.8 (2,891)",
                price = Random.nextFloat() * (10.0f - 0.5f) + 0.5f,
                favorited=false
            ),
            Destination(
                id = 3,
                name = "Candi Sewu",
                location = "Klaten, Central Java",
                rating = "4.7 (1,523)",
                price = Random.nextFloat() * (10.0f - 0.5f) + 0.5f,
                favorited=false
            ),
            Destination(
                id = 4,
                name = "Candi Plaosan",
                location = "Klaten, Central Java",
                rating = "4.8 (872)",
                price = Random.nextFloat() * (10.0f - 0.5f) + 0.5f,
                favorited=false
            ),
            Destination(
                id = 5,
                name = "Candi Dieng",
                location = "Wonosobo, Jawa Tengah",
                rating = "4.7 (942)",
                price = Random.nextFloat() * (10.0f - 0.5f) + 0.5f,
                favorited=false
            ),
            Destination(
                id = 6,
                name = "Candi Gedong Songo",
                location = "Semarang, Central Java",
                rating = "4.6 (532)",
                price = Random.nextFloat() * (10.0f - 0.5f) + 0.5f,
                favorited=false
            ),
            Destination(
                id = 7,
                name = "Candi Penataran",
                location = "Blitar, East Java",
                rating = "4.8 (1,231)",
                price = Random.nextFloat() * (10.0f - 0.5f) + 0.5f,
                favorited=false
            ),
            Destination(
                id = 8,
                name = "Candi Kalasan",
                location = "Sleman, Yogyakarta",
                rating = "4.8 (624)",
                price = Random.nextFloat() * (10.0f - 0.5f) + 0.5f,
                favorited=false
            ),
            Destination(
                id = 9,
                name = "Pantai Pandawa",
                location = "Kutuh, Bali",
                rating = "4.9 (2,143)",
                price = Random.nextFloat() * (10.0f - 0.5f) + 0.5f,
                favorited=false
            ),
            Destination(
                id = 10,
                name = "Pura Tanah Lot",
                location = "Tabanan, Bali",
                rating = "4.9 (1,823)",
                price = Random.nextFloat() * (10.0f - 0.5f) + 0.5f,
                favorited=false
            )
        )
    }
    fun getImageDestination():List<ImageDestination>{
        return listOf(
            ImageDestination(
                id = 1,
                imageUrl = "candi_borobudur",
                idDestination = 1,
            ),
            ImageDestination(
                id = 2,
                imageUrl = "borobudur2",
                idDestination = 1,
            ),
            ImageDestination(
                id = 3,
                imageUrl = "borobudur3",
                idDestination = 1,
            ),
            ImageDestination(
                id = 4,
                imageUrl = "candi_prambanan",
                idDestination = 2,
            ),
            ImageDestination(
                id = 5,
                imageUrl = "candi_sewu",
                idDestination = 3,
            ),
            ImageDestination(
                id = 6,
                imageUrl = "candi_plaosan",
                idDestination = 4,
            ),
            ImageDestination(
                id = 7,
                imageUrl = "candi_dieng",
                idDestination = 5,
            ),
            ImageDestination(
                id = 8,
                imageUrl = "candi_gedong_songo",
                idDestination = 6,
            ),
            ImageDestination(
                id = 9,
                imageUrl = "candi_penataran",
                idDestination = 7,
            ),
            ImageDestination(
                id = 10,
                imageUrl = "candi_kalasan",
                idDestination = 8,
            ),
            ImageDestination(
                id = 11,
                imageUrl = "pantai_pandawa",
                idDestination = 9,
            ),
            ImageDestination(
                id = 12,
                imageUrl = "pura_tanah_lot",
                idDestination = 10,
            ),
        )
    }

    fun getAboutDestination():List<AboutDestination>{
        return listOf(
            AboutDestination(
                id=1,
                about = "Candi Borobudur, sebuah peninggalan monumental Buddha di Pulau Jawa, Indonesia, memiliki sejarah yang memikat sepanjang berabad-abad. Dibangun pada abad ke-8 atau ke-9 Masehi, Borobudur merupakan karya megah dinasti Syailendra, dengan Raja Samaratungga sebagai pionir pembangunan. Rancangan arsitekturnya yang mengesankan mencerminkan kecakapan teknik dan spiritualitas, terdiri dari sembilan tingkat yang melibatkan 504 arca Buddha dan ribuan panel relief yang menggambarkan ajaran Buddha.\n\n" +
                        "Sayangnya, setelah masa kejayaannya, Borobudur terlupakan dan tertutup oleh letusan Gunung Merapi serta tanah longsor. Pada abad ke-19, Sir Thomas Stamford Raffles menemukan kembali keajaiban ini dan memulai upaya restorasi. Proses pemugaran berlanjut pada abad ke-20 oleh pemerintah Hindia Belanda dan kemudian oleh pemerintah Indonesia setelah merdeka. Borobudur secara resmi diakui oleh UNESCO sebagai Situs Warisan Dunia pada tahun 1973, mengukuhkan statusnya sebagai salah satu cagar budaya paling berharga di dunia.\n\n" +
                        "Sejak saat itu, Borobudur telah menjadi destinasi pariwisata utama, menarik wisatawan dari seluruh dunia untuk menyaksikan keindahan dan spiritualitasnya. Pemerintah Indonesia terus berkomitmen untuk melestarikan Borobudur, menjadikannya bukan hanya sebagai simbol sejarah Buddha, tetapi juga sebagai warisan budaya yang abadi bagi generasi mendatang.",
                idDestination = 1,
            ),
            AboutDestination(
                id=2,
                about = "Candi Prambanan, sebuah kompleks kuil Hindu yang memukau, terletak di Yogyakarta, Indonesia, dan memiliki sejarah yang menarik. Dibangun pada abad ke-9 Masehi oleh dinasti Mataram, Prambanan menjadi simbol kejayaan agama Hindu-Buddha di pulau Jawa pada masa itu. Konon, pembangunan candi ini dilakukan atas perintah Raja Balitung Maha Sambu, yang ingin menciptakan kuil untuk menghormati Trimurti, yakni Brahma, Vishnu, dan Shiva, tiga dewa utama dalam agama Hindu.\n\n" +
                        "Arsitektur Prambanan mencerminkan kemegahan dan keahlian tangan-tangan ahli arsitek pada masanya. Terdiri dari tiga candi utama yang menghormati masing-masing dewa, yaitu Candi Shiva, Candi Vishnu, dan Candi Brahma, serta 224 candi kecil yang mengelilingi kompleks utama. Relief-relief yang menghiasi dinding candi menggambarkan kisah-kisah epik Ramayana dan Mahabharata, memberikan sentuhan artistik dan spiritual yang mendalam.\n\n" +
                        "Sayangnya, seperti banyak situs bersejarah, Prambanan juga mengalami kerusakan akibat gempa bumi dan letusan gunung berapi. Pada abad ke-10, gempa bumi melanda dan mengakibatkan kerusakan cukup serius pada kompleks ini. Namun, upaya pemugaran dilakukan pada abad ke-20 oleh pemerintah Indonesia, dan Prambanan kembali bersinar sebagai situs warisan dunia yang diakui oleh UNESCO pada tahun 1991.\n\n" +
                        "Hingga kini, Candi Prambanan tidak hanya menjadi destinasi wisata yang populer tetapi juga menyimpan kekayaan sejarah dan budaya Indonesia. Keindahan arsitektur dan nilai spiritual yang terkandung di dalamnya membuat Prambanan menjadi salah satu warisan berharga yang perlu dijaga dan dihargai oleh generasi-generasi mendatang.",
                idDestination = 2,
            ),
            AboutDestination(
                id=3,
                about = "Candi Sewu, yang secara harfiah berarti \"seribu candi\" dalam bahasa Jawa, adalah kompleks candi Buddha yang terletak tidak jauh dari Candi Borobudur di Pulau Jawa, Indonesia. Dibangun pada abad ke-8 dan ke-9 Masehi oleh dinasti Syailendra, Candi Sewu adalah salah satu kompleks candi Buddha terbesar di Indonesia. Awalnya, kompleks ini mungkin dibangun sebagai tempat pemujaan Buddha dan untuk memperingati kemenangan agama Buddha di wilayah tersebut.\n\n" +
                        "Candi Sewu memiliki arsitektur yang mengesankan dengan 249 candi kecil yang tersusun dalam tiga kelompok persegi panjang. Candi utama, yang juga dikenal sebagai Manjusrigrha, merupakan bangunan utama di kompleks ini. Sebagai bagian dari rangkaian kompleks candi di daerah tersebut, Candi Sewu menjadi saksi perkembangan agama Buddha dan kejayaan dinasti Syailendra pada masa itu. Namun, seiring berjalannya waktu, pengaruh Buddha di pulau Jawa berkurang, dan Candi Sewu seperti banyak candi lainnya mengalami masa pengabaian.\n\n" +
                        "Pada abad ke-19, serupa dengan perjalanan Candi Borobudur, Candi Sewu ditemukan kembali oleh penjelajah Eropa, termasuk Sir Thomas Stamford Raffles. Proses pemugaran dimulai pada abad ke-20 untuk memulihkan keindahan dan keagungan aslinya. Sejak itu, Candi Sewu telah menjadi destinasi wisata yang menarik, menarik pengunjung dengan keindahan arsitektur dan reliefnya yang menggambarkan kisah-kisah Buddha. Sebagai bagian dari Situs Warisan Dunia Borobudur, Candi Sewu terus menyimpan kekayaan sejarah dan kebudayaan Indonesia yang mendalam.",
                idDestination = 3,
            ),
            AboutDestination(
                id=4,
                about = "Candi Plaosan, juga dikenal sebagai Candi Plaosan Lor dan Candi Plaosan Kidul, adalah kompleks candi Buddha yang terletak di dekat Yogyakarta, Jawa Tengah, Indonesia. Pembangunan candi ini diperkirakan terjadi pada abad ke-9, pada masa pemerintahan Wangsa Sanjaya. Candi Plaosan menggambarkan penggabungan antara gaya arsitektur Hindu dan Buddha, menciptakan situs yang unik dan menarik.\n\n" +
                        "Dua candi utama di kompleks ini, yaitu Candi Plaosan Lor dan Candi Plaosan Kidul, memiliki ciri khas arsitektur yang menggabungkan elemen-elemen Buddha dan Hindu. Dinding-dinding candi dihiasi dengan relief yang menggambarkan kisah-kisah Buddha, kehidupan sehari-hari masyarakat pada masa itu, serta dewa-dewa Hindu. Keberagaman seni dan budaya yang tercermin dalam relief-relief ini memberikan pemahaman mendalam tentang kehidupan dan kepercayaan masyarakat pada masa lampau.\n\n" +
                        "Pada beberapa relief, terdapat pula inskripsi-inskripsi yang memberikan informasi tentang pembangunan candi dan pemberian sumbangan oleh para pendukungnya. Meskipun tidak sebesar Borobudur atau Prambanan, Candi Plaosan tetap memegang nilai sejarah yang tinggi dan menjadi saksi bisu peradaban Jawa pada masa lampau. Keberadaannya sebagai situs warisan budaya menunjukkan kekayaan warisan sejarah Indonesia yang terus dijaga dan dilestarikan. Dengan pesona arsitektur yang memesona dan nilai sejarahnya yang mendalam, Candi Plaosan menjadi destinasi wisata yang menarik bagi para pengunjung yang ingin menjelajahi kekayaan budaya Jawa.",
                idDestination = 4,
            ),
            AboutDestination(
                id=5,
                about = "Candi Dieng, kompleks candi kuno yang terletak di dataran tinggi Dieng, Jawa Tengah, Indonesia, menyimpan sejarah yang kaya dan beragam. Diduga dibangun pada abad ke-7 hingga ke-8 Masehi, candi-candi ini mencerminkan kejayaan peradaban Hindu-Buddha di wilayah tersebut.\n\n" +
                        "Candi-candi Dieng terdiri dari delapan kompleks candi, yang sebagian besar didedikasikan untuk dewa-dewa Hindu, seperti Dewi Shiva dan Dewi Parvati. Arsitektur candi-candi ini menunjukkan pengaruh India kuno, tetapi dengan karakteristik unik yang mencerminkan adaptasi budaya lokal. Relief-relief yang menghiasi dinding candi menggambarkan adegan-kehidupan sehari-hari, mitologi Hindu, dan ajaran Buddha, memberikan pandangan mendalam tentang kepercayaan dan kehidupan masyarakat pada masa itu.\n\n" +
                        "Selama berabad-abad, Candi Dieng mengalami perubahan kepemilikan dan peningkatan. Pada abad ke-6, kompleks ini ditinggalkan, mungkin akibat letusan gunung berapi atau perubahan sosial. Pada abad ke-19, pemerintah Hindia Belanda menemukan kembali kompleks ini, dan sejak itu, berbagai proyek pelestarian dan pemugaran telah dilakukan untuk mempertahankan warisan bersejarah ini.\n\n" +
                        "Hingga saat ini, Candi Dieng tetap menjadi daya tarik utama bagi para pelancong dan peneliti arkeologi. Keindahan alam dataran tinggi Dieng yang menakjubkan, disertai dengan atmosfer mistis candi-candi kuno, menjadikannya destinasi yang menggabungkan sejarah, kebudayaan, dan keindahan alam.",
                idDestination = 5,
            ),
            AboutDestination(
                id=6,
                about = "Candi Gedong Songo, yang terletak di lereng Gunung Ungaran, Jawa Tengah, Indonesia, memiliki sejarah yang mencakup unsur keagamaan dan keindahan arsitektur. Dibangun pada abad ke-9 hingga ke-10 Masehi, candi ini merupakan kompleks kuil Hindu yang terdiri dari sembilan candi kecil yang tersebar di perbukitan. Peninggalan ini diyakini merupakan hasil dari pengaruh Hindu-Buddha yang kuat pada masa itu di Jawa.\n\n" +
                        "Seiring berjalannya waktu, Candi Gedong Songo mengalami berbagai kerusakan dan perubahan. Pada abad ke-19, setelah penemuan kembali oleh Belanda, upaya pemugaran dilakukan untuk mengembalikan kejayaan dan keindahan aslinya. Restorasi ini melibatkan perbaikan struktur bangunan dan pengembalian elemen arsitektur yang hilang.\n\n" +
                        "Candi Gedong Songo juga memiliki nilai sejarah sebagai tempat ibadah Hindu pada masa lalu. Terdapat patung-patung Dewa Siwa dan relief-relief yang menggambarkan kisah-kisah epik Hindu, seperti Ramayana dan Mahabharata. Kompleks candi ini menciptakan atmosfer mistis dengan pemandangan alam yang memukau, menawarkan pengalaman unik bagi para pengunjung yang tertarik dengan sejarah dan spiritualitas.\n\n" +
                        "Hingga kini, Candi Gedong Songo tetap menjadi situs bersejarah yang menarik minat wisatawan lokal dan internasional. Keberadaannya memberikan kesempatan bagi pengunjung untuk menyelami kekayaan budaya dan sejarah Indonesia melalui keindahan arsitektur dan lanskap alam yang memukau.",
                idDestination = 6,
            ),
            AboutDestination(
                id=7,
                about = "Candi Penataran, sebuah kompleks candi Hindu yang terletak di daerah Blitar, Jawa Timur, Indonesia, memiliki sejarah yang menarik sepanjang berabad-abad. Dibangun pada abad ke-12 Masehi, candi ini merupakan salah satu peninggalan dari masa kejayaan Kerajaan Majapahit. Pada masa itu, Raja Wijaya memerintahkan pembangunan Candi Penataran sebagai bagian dari kompleks yang luas, yang dipersembahkan untuk memuja dewa-dewa Hindu.\n\n" +
                        "Candi Penataran memiliki desain yang mengesankan dengan struktur yang rumit dan patung-patung yang megah. Kompleks candi ini mencerminkan pengaruh agama Hindu dan Budha, menunjukkan toleransi beragama yang tinggi pada masa Majapahit. Selain sebagai tempat ibadah, Candi Penataran juga berfungsi sebagai pusat kegiatan keagamaan dan kebudayaan pada zamannya.\n\n" +
                        "Seiring berjalannya waktu, kejayaan Majapahit meredup, dan kompleks Candi Penataran pun mengalami kemunduran. Pada abad ke-19, penjajah Belanda melakukan upaya pemugaran untuk mengembalikan kejayaan candi ini. Namun, upaya tersebut tidak sepenuhnya berhasil, dan Candi Penataran tetap menyimpan keindahan dan misteri sejarahnya. Hari ini, candi ini menjadi objek wisata sejarah yang menarik, menarik pengunjung dengan keanggunan arsitekturnya dan cerita yang terkandung di setiap relief dan patungnya.",
                idDestination = 7,
            ),
            AboutDestination(
                id=8,
                about = "Candi Kalasan, salah satu candi Buddha di Pulau Jawa, Indonesia, memiliki sejarah yang mencengangkan. Dibangun pada abad ke-8 atau ke-9 Masehi, candi ini tergolong dalam periode pemerintahan dinasti Syailendra, yang juga membangun Borobudur. Diketahui bahwa Candi Kalasan didirikan sebagai kompleks keagamaan untuk memperingati pernikahan antara pewaris takhta, yaitu Rakai Panangkaran, dengan seorang putri Wangsa Syailendra.\n\n" +
                        "Candi Kalasan memiliki arsitektur yang unik dengan gaya candi Buddha Gupta, dan dikenal karena ukiran reliefnya yang memukau. Relief tersebut menggambarkan ajaran Buddha, kisah-kisah dari Jataka, dan juga mencerminkan pengaruh seni India. Pada masa kejayaannya, Candi Kalasan dipercaya menjadi pusat kegiatan keagamaan dan pendidikan Buddha di wilayah tersebut.\n\n" +
                        "Sayangnya, seperti banyak candi Buddha lainnya di Jawa, Candi Kalasan mengalami kerusakan seiring berjalannya waktu dan perubahan politik. Pada abad ke-19, upaya pemugaran dimulai, dan sejak itu, berbagai proyek restorasi telah dilakukan untuk memulihkan keindahan dan keagungan aslinya. Hari ini, Candi Kalasan terbuka untuk umum dan menjadi saksi bisu dari kejayaan peradaban Buddha di Indonesia pada masa lalu.",
                idDestination = 8,
            ),
            AboutDestination(
                id=9,
                about = "Pantai Pandawa, sebuah destinasi eksotis di Bali, Indonesia, memiliki sejarah yang relatif lebih baru dibandingkan dengan situs-situs bersejarah lainnya. Terletak di selatan Bali, pantai ini dikenal sebagai destinasi wisata yang indah dan menyuguhkan pasir putih yang lembut serta air laut yang jernih. Namun, sebelum menjadi destinasi wisata populer, Pantai Pandawa memiliki cerita yang menarik.\n\n" +
                        "Pada awalnya, akses ke Pantai Pandawa sangat terbatas karena dikelilingi oleh tebing-tebing karang yang tinggi. Pada tahun 2012, pemerintah setempat melakukan proyek pengembangan untuk membuat akses lebih mudah dengan mengukir tebing-tebing karang yang menjulang. Proyek ini melibatkan upaya besar dalam membuka jalan dan membuat terowongan untuk memungkinkan akses langsung ke pantai. Setelah beberapa tahun perjuangan, Pantai Pandawa dibuka untuk umum pada tahun 2015.\n\n" +
                        "Sejak pembukaannya, Pantai Pandawa telah menjadi destinasi favorit bagi wisatawan yang mencari keindahan alam dan kehidupan pantai yang tenang. Pasir putih yang lembut dan air laut yang biru memikat pengunjung untuk bersantai di tepi pantai atau beraktivitas seperti snorkeling. Warung-warung pantai dan penyewaan peralatan wisata air turut berkembang, memberikan tambahan nilai bagi pengalaman wisata di Pantai Pandawa.\n\n" +
                        "Pantai Pandawa bukan hanya destinasi wisata, tetapi juga menjadi bagian dari upaya pengembangan pariwisata di Bali. Keberhasilan transformasinya dari lokasi tersembunyi menjadi daya tarik utama adalah cerminan dari komitmen pemerintah dan masyarakat lokal dalam mengembangkan potensi pariwisata daerah tersebut.",
                idDestination = 9,
            ),
            AboutDestination(
                id=10,
                about = "Pura Tanah Lot, sebuah kompleks pura di Bali, Indonesia, memiliki sejarah yang kaya dan erat kaitannya dengan kepercayaan Hindu di pulau ini. Pura Tanah Lot dibangun pada abad ke-16 oleh seorang pendeta Hindu bernama Dang Hyang Nirartha. Menurut legenda, Nirartha melakukan perjalanan ke seluruh pulau Bali dan memilih Tanah Lot sebagai tempat untuk meditasi karena keindahan alamnya. Beliau juga dianggap sebagai penggagas sistem irigasi subak di Bali.\n\n" +
                        "Seiring berjalannya waktu, Pura Tanah Lot menjadi salah satu pura paling suci dan populer di Bali. Konstruksi pura ini diperbarui dan diperluas oleh keturunan Nirartha. Keunikan Pura Tanah Lot adalah letaknya yang menjulang di atas batu karang besar di tepi laut, memberikan pemandangan dramatis terutama saat matahari terbenam. Meskipun demikian, erosi dan abrasi laut telah menimbulkan tantangan bagi kelestarian struktur ini.\n\n" +
                        "Pada tahun 1980-an, Pura Tanah Lot mengalami upaya pemugaran dan restorasi yang signifikan untuk mengatasi dampak kerusakan akibat air laut. Tempat ini menjadi daya tarik utama bagi wisatawan, tidak hanya karena nilai spiritualnya tetapi juga karena keindahan alamnya. Pura Tanah Lot kini menjadi salah satu ikon pariwisata Bali dan terus memegang peranan penting dalam kehidupan keagamaan masyarakat setempat.",
                idDestination = 10,
            ),
        )
    }
}

