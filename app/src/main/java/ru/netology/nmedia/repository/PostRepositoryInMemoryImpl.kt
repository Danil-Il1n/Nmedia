package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L

    private var posts = listOf(
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "23 сентября в 10:12",
            content = "Освоение новой профессии — это не только открывающиеся возможности и перспективы, но и настоящий вызов самому себе. Приходится выходить из зоны комфорта и перестраивать привычный образ жизни: менять распорядок дня, искать время для занятий, быть готовым к возможным неудачам в начале пути. В блоге рассказали, как избежать стресса на курсах профпереподготовки → http://netolo.gy/fPD",
            likedByMe = false,
            likes = 90,
            sharedByMe = false,
            shared = 1

        ), Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "22 сентября в 10:14",
            content = "Делиться впечатлениями о любимых фильмах легко, а что если рассказать так, чтобы все заскучали \uD83D\uDE34\n",
            likedByMe = false,
            likes = 80,
            sharedByMe = false,
            shared = 1
        ), Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "22 сентября в 10:12",
            content = "Таймбоксинг — отличный способ навести порядок в своём календаре и разобраться с делами, которые долго откладывали на потом. Его главный принцип — на каждое дело заранее выделяется определённый отрезок времени. В это время вы работаете только над одной задачей, не переключаясь на другие. Собрали советы, которые помогут внедрить таймбоксинг \uD83D\uDC47\uD83C\uDFFB",
            likedByMe = false,
            likes = 70,
            sharedByMe = false,
            shared = 1
        ), Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 сентября в 10:12",
            content = "\uD83D\uDE80 24 сентября стартует новый поток бесплатного курса «Диджитал-старт: первый шаг к востребованной профессии» — за две недели вы попробуете себя в разных профессиях и определите, что подходит именно вам → http://netolo.gy/fQ",
            likedByMe = false,
            likes = 60,
            sharedByMe = false,
            shared = 1
        ), Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "20 сентября в 10:14",
            content = "Диджитал давно стал частью нашей жизни: мы общаемся в социальных сетях и мессенджерах, заказываем еду, такси и оплачиваем счета через приложения.",
            likedByMe = false,
            likes = 50,
            sharedByMe = false,
            shared = 1
        ), Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "19 сентября в 14:12",
            content = "Большая афиша мероприятий осени: конференции, выставки и хакатоны для жителей Москвы, Ульяновска и Новосибирска \uD83D\uDE09",
            likedByMe = false,
            likes = 40,
            sharedByMe = false,
            shared = 1
        ), Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "19 сентября в 10:24",

            content = "Языков программирования много, и выбрать какой-то один бывает нелегко. Собрали подборку статей, которая поможет вам начать, если вы остановили свой выбор на JavaScript.",
            likedByMe = false,
            likes = 30,
            sharedByMe = false,
            shared = 1
        ), Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "18 сентября в 10:12",
            content = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях \uD83D\uDC47",
            likedByMe = false,
            likes = 20,
            sharedByMe = false,
            shared = 1
        ), Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
            likes = 10,
            sharedByMe = false,
            shared = 999
        )
    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(likedByMe = !it.likedByMe, likes = if (it.likedByMe) it.likes - 1 else it.likes + 1)
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(sharedByMe = !it.sharedByMe, shared = it.shared + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter {
            it.id != id
        }
        data.value = posts
    }

    override fun save(post: Post) {
        posts = if (post.id == 0L) {
            // Новый пост
            listOf(
                post.copy(
                    id = nextId++,
                    published = "Now",
                    author = "Me",
                    likedByMe = false
                )
            ) + posts
        } else {
            // Обновление существующего поста
            posts.map {
                if (it.id == post.id) post else it
            }
        }
        data.value = posts
    }
}