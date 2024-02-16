package pidev.javafx.Controller.Tools;

public interface MyListener<T> {
    default void onClickListener(T arg){}
     default void exit(){}
}
