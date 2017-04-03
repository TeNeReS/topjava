package ru.javawebinar.topjava.util;


import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.util.exception.NotFoundException;

public class ValidationUtil {

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static <T> T checkAccess(T object) {
        if (object == null) {
            throw new NotFoundException("Access denied");
        }
        return object;
    }

    public static void checkAccessAndExistence(boolean found, int id) {
        checkAccessAndExistence(found, "id=" + id);
    }

    public static <T> T checkAccessAndExistence(T object, int id) {
        return checkAccessAndExistence(object, "id=" + id);
    }

    public static <T> T checkAccessAndExistence(T object, String msg) {
        checkAccessAndExistence(object != null, msg);
        return object;
    }

    public static void checkAccessAndExistence(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Access denied or not found entity with " + msg);
        }
    }

    public static void checkNew(BaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void checkIdConsistent(BaseEntity entity, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}