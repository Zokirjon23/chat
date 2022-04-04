package uz.example.chat.repository

import uz.example.chat.model.User
import uz.example.chat.db.local.MyPreferences
import uz.example.chat.db.local.UserDao
import uz.example.chat.db.remote.UserService

class MainRepositoryImpl(
    private val myPreferences: MyPreferences,
    private val userService: UserService,
    private val userDao: UserDao
) : MainRepository {

    override fun setUserNumber(string: String) =
        myPreferences.setUserNumber(string)


    override fun getUserNumber(): String {
        return myPreferences.getUserNumber()
    }

    override fun setUserName(userName: String) =
        myPreferences.setUserName(userName)


    override fun getUserName() = myPreferences.getUserName()


    override fun setUserId(userId: Long) =
        myPreferences.setUserId(userId)


    override fun getUserId() = myPreferences.getUserId()


    override suspend fun sendUser(user: User) = userService.sendUser(user)


    override suspend fun insertAppContacts(list: List<User>) {
        userDao.deleteAll()
        userDao.insertAll(list)
    }

    override suspend fun insertContact(user: User) = userDao.insert(user)


    override suspend fun getAppContacts() = userDao.getAllContact()


    override suspend fun sendAllContact(contacts: ArrayList<User>) =
        userService.sendContacts(contacts as List<User>)

}