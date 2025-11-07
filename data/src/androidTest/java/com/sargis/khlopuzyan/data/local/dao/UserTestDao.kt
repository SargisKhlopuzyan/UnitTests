package com.sargis.khlopuzyan.data.local.dao

import com.sargis.khlopuzyan.data.local.entity.UserEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

// Assertions (Jupiter version) is not working here mut be enabled in gradle file
class UserTestDao : KoinTest {

    private val noteDao: UserDao by inject()
//    private val database: Database by inject()

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
//        stopKoin()
//        database.close()
    }

    @Test
    fun `insertingUser_addsUserInLocalDb`() = runTest {
        val testUserEntity = UserEntity(
            id = 1,
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            username = "SargisKh1",
            password = "a1234"
        )
        noteDao.insertUser(testUserEntity)

        Assert.assertNotNull(noteDao.getUserById(1))
//        Assertions.assertNotNull(noteDao.getUserById(1))
    }

    @Test
    fun `deletingInsertedUser_addsAndRemoveUserInLocalDb`() = runTest {
        val testUserEntity = UserEntity(
            id = 2,
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            username = "SargisKh2",
            password = "a12345"
        )
        noteDao.insertUser(testUserEntity)

        Assert.assertNotNull(noteDao.getUserById(testUserEntity.id!!))

        noteDao.deleteUser(testUserEntity)
        Assert.assertNull(noteDao.getUserById(testUserEntity.id))
    }

    @Test
    fun `gettingInsertedUser_addsUserInLocalDbAndReturnsUserByUsername`() = runTest {
        val testUserEntity = UserEntity(
            id = 3,
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            username = "SargisKh3",
            password = "a12345"
        )
        noteDao.insertUser(testUserEntity)

        Assert.assertNotNull(noteDao.getUserByUsername(testUserEntity.username))
    }

    @Test
    fun `gettingInsertedUser_addsUserInLocalDbAndReturnsUserByUsernameAndPassword`() = runTest {
        val testUserEntity = UserEntity(
            id = 4,
            firstName = "Sargis",
            lastName = "Khlopuzyan",
            username = "SargisKh4",
            password = "a12345"
        )
        noteDao.insertUser(testUserEntity)

        Assert.assertNotNull(
            noteDao.getUserByUsernameAndPassword(
                testUserEntity.username,
                testUserEntity.password
            )
        )
    }
}