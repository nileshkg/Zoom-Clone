package com.app.clonedzoom.chat.qb;

import android.util.SparseArray;

import com.quickblox.users.model.QBUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Basically in your app you should store users in database
 * And load users to memory on demand
 * We're using runtime SpaceArray holder just to simplify app logic
 */
public class QbUsersHolder {

    private static QbUsersHolder instance;

    private SparseArray<QBUser> qbUserSparseArray;

    public static synchronized QbUsersHolder getInstance() {
        if (instance == null) {
            instance = new QbUsersHolder();
        }

        return instance;
    }

    private QbUsersHolder() {
        qbUserSparseArray = new SparseArray<>();
    }

    public void putUsers(List<QBUser> users) {
        for (QBUser user : users) {
            if (user != null) {
                putUser(user);
            }
        }
    }

    public void putUser(QBUser user) {
        qbUserSparseArray.put(user.getId(), user);
    }

    public QBUser getUserById(int id) {
        return qbUserSparseArray.get(id);
    }

    public List<QBUser> getUsersByIds(List<Integer> ids) {
        List<QBUser> users = new ArrayList<>();
        for (Integer id : ids) {
            QBUser user = getUserById(id);
            if (user != null) {
                users.add(user);
            }
        }

        return users;
    }

    public boolean hasAllUsers(List<Integer> usersIds) {
        for (Integer userId : usersIds) {
            if (qbUserSparseArray.get(userId) == null) {
                return false;
            }
        }
        return true;
    }
}