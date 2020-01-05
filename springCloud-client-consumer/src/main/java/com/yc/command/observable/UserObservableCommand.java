//package com.yc.command.observable;
//
//import com.netflix.hystrix.HystrixObservableCommand;
//import org.springframework.web.client.RestTemplate;
//import rx.Observable;
//
///**
// * Created by yuche on 2019/9/13.
// */
//public class UserObservableCommand extends HystrixObservableCommand<String> {
//    private RestTemplate restTemplate;
//    private Long id;
//
//    public UserObservableCommand(Setter setter, RestTemplate restTemplate, Long id) {
//        super(setter);
//        this.restTemplate = restTemplate;
//        this.id = id;
//    }
//
//    protected Observable<String> construct() {
//        return Observable.create(new Observable.on)
//    }
//}
