package watch.movie.security;

public class DevstatException extends Exception{
    DevstatException(ErrorCode msg){
        super(String.valueOf(msg));
    }
}
