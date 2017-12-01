package base;

/**
 * Created by DELL on 2017/11/14.
 */

public interface BaseView<T> {
     void RequestSuccess(T t);
     void RequestFailure(T t);
     void Failure(T t);


}
