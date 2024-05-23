package com.example.testreactivee;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Flow;

public class CreationReactiveObjTests {


    @Test
    public void createAFlux_just(){
        Flux<String> flux = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry")
                ;
        flux.log().subscribe(t ->{
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(t);
        });



        System.out.println("This thread");

        StepVerifier.create(flux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }


    @Test
    public void fromArray(){
        String[] array = new String[] {"Apple", "Orange", "Grape", "Banana", "Strawberry"};

        Flux<String> flux = Flux.fromArray(array); // також існує fromIterable, fromStream
        flux.subscribe(System.out::println);

        StepVerifier.create(flux)
                .expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }


    @Test
    public void mergeFluxes() throws InterruptedException {

        Flux<String> characterFlux = Flux
                .just("Garfield", "Kojak", "Barbossa")
                .delayElements(Duration.ofMillis(500));
        Flux<String> foodFlux = Flux
                .just("Lasagna", "Lollipops", "Apples")
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));


        Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux);
        mergedFlux.subscribe(t ->{
            System.out.println(t);
        });

        StepVerifier.create(mergedFlux)
                .expectNext("Garfield")
                .expectNext("Lasagna")
                .expectNext("Kojak")
                .expectNext("Lollipops")
                .expectNext("Barbossa")
                .expectNext("Apples")
                .verifyComplete();
    }


    @Test
    public void testZip() throws InterruptedException {
        Flux flux1 = Flux.just("Garfield", "Kojak", "Barbossa");
        Flux flux2 = Flux.just("Lasagna", "Lollipops", "Apples")
                .delayElements(Duration.ofSeconds(1)); // в любому випадку zip буде очікувати елемент
        // і все рівно буде повертати кортежі

        Flux<Tuple2<String,String>> flux = Flux.zip(flux1,flux2);
        flux.subscribe(t ->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(t);
        });


        //  В zip також можна передати свій комбінатор, но знайшов метод лише з 2 вхідними flux (ст. 321)

//        Якщо є delayElements то буде створено паралельний потік, а якщо немає - буде працювати після
//        підписника зверху
//        flux.subscribe(t ->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(t +" Test");
//        });

        System.out.println("Hello1");
        Thread.sleep(5000);
        System.out.println("Hello1");

        StepVerifier.create(flux)
                .expectNextMatches(p ->
                        p.getT1().equals("Garfield") &&
                                p.getT2().equals("Lasagna"))
                .expectNextMatches(p ->
                        p.getT1().equals("Kojak") &&
                                p.getT2().equals("Lollipops"))
                .expectNextMatches(p ->
                        p.getT1().equals("Barbossa") &&
                                p.getT2().equals("Apples"))
                .verifyComplete();

    }


    @Test
    public void firstWithSignalFlux() {

        Flux<String> slowFlux = Flux.just("tortoise", "snail", "sloth")
                .delaySubscription(Duration.ofMillis(100));
        Flux<String> fastFlux = Flux.just("hare", "cheetah", "squirrel");

        Flux<String> firstFlux = Flux.firstWithSignal(slowFlux, fastFlux); // вибирається лише один
        // Flux дані з якого надходять першими (ст. 322)

        StepVerifier.create(firstFlux)
                .expectNext("hare")
                .expectNext("cheetah")
                .expectNext("squirrel")
                .verifyComplete();
    }


    @Test
    public void testSkipAndMap(){
        Flux<String> flux = Flux.just("one", "two", "skip a few", "ninety nine", "one hundred")
                .delayElements(Duration.ofSeconds(1)) // для наступних елементів розпочинається лише
                // після опрацювання попереднього
                .skip(Duration.ofSeconds(3))// також можна передати просто число (к-сть елементів, що
                // будуть пропущені)
                .map(t -> {
                    System.out.println("Map--------------");
                    return t.toUpperCase();
                })
                .map(t -> {
                    System.out.println("Map2-----------");
                    return t.toLowerCase();
                }); // по тому що я побачив елементи передаються, обробляються і відправляються дальше
        // по одному

        flux.subscribe(t ->{
            System.out.println(t);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Hello1");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hello2");
//        StepVerifier.create(flux)
//                .expectNext("ninety nine")
//                .expectNext("one hundred")
//                .verifyComplete();
    }


    @Test
    public void testRange(){
        Flux<Integer> flux = Flux.range(1,10).take(5); // take обмежує к-сть елементів які
        // буде отримано
        // також в take є друга форма з Duration яка буде випускати елементи лише якийсь час і при
        // його закінчені завершить Flux

        StepVerifier.create(flux)
                .expectNext(1,2,3,4,5)
                .verifyComplete();
    }


    @Test
    public void testFilterAndDistinct(){
            Flux<Integer> integerFlux = Flux.just(1,2,2,3,3,5,6,7,8,8,8,10,2,1,4,2,6,5,3,1,2,2,22,1,22,3,3)
                    .filter(i -> i %2 == 0)
                    .distinct();

            integerFlux.subscribe(t ->{
                System.out.println(t);
            });


        StepVerifier.create(integerFlux)
                .expectNext(2,6,8,10,4,22)
                .verifyComplete();
    }

    @Test
    public void testFlaMap(){
        Random random = new Random();
        Flux<String> flux = Flux.just("one", "two", "skip a few", "ninety nine", "one hundred")
                .delayElements(Duration.ofMillis(400)).log() // якщо я правильно розумію кожен елемент буде оброблятись
                // в своєму потоці оскільки в delayElements присутнє Schedulers.parallel()
                .flatMap(s -> Mono.just(s)
                        .map(t -> {
//                            System.out.println("Map--------------");
                            return t.toUpperCase();
                        })
                        .map(t -> {
//                            try {
//                                int i = random.nextInt(1000)+500;
//                                Thread.sleep(i);
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            } // текст чи можуть значення виходити в іншому порядку від заданого в just
//                            System.out.println("Map2-----------");
                            return t.toLowerCase();
                        }).subscribeOn(Schedulers.parallel()).log()
                        ).log(); //  в даному випадку з flatMap delayElements починає відлік зразу після
        // входження елемента, а не очікуває завершення всіх дій із значенням (щоб затестити можеш
        // добавити Thread.sleep(900) в один із мапів Mono)

        flux.subscribe(t ->{
//            try {
//                int i = random.nextInt(1000)+200;
//                Thread.sleep(i);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } // тест чи працює потік Mono під час виконання даного subscribe
            System.out.println(t);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }); // Значення заходять в порядку їх опрацювання FlatMap (даний потік є потоком нашого
        // Flux.just("one"....)


        System.out.println("Hello1");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("Hello2");
//        StepVerifier.create(flux)
//                .expectNext("ninety nine")
//                .expectNext("one hundred")
//                .verifyComplete();
    }


    @Test
    public void testBuffer(){
        Flux<String> stringFlux = Flux.just("one", "two", "skip a few", "ninety nine", "one hundred");

        Flux<List<String>> flux = stringFlux.buffer(3); // оскільки ми розбиваємо наш набір даних на колеції
        // це призводить до втрати реактивності. Оскільки ми не можемо так гнучко налаштовувати обробку кожного значення
        // та сама по собі колекція що містить багато даних буде зменшувати к-сть потоків тим самим зменшуючи швидкість
        // У такому випадку можна комбінувати buffer з flatMap - 330 ст.
         flux.flatMap(t -> Flux.fromIterable(t).subscribeOn(Schedulers.parallel()).log()).log().subscribe(System.out::println);

//        flux.subscribe(System.out::println);
    }

    @Test
    public void testCollectTo(){
        Flux<String> animalFlux = Flux.just(
                "aardvark", "elephant", "koala", "eagle", "kangaroo");

//        Flux<List<String>> listFlux = animalFlux.buffer(); // якщо не вказати значення бафера то всі обєкти будуть в 1 колекції

//        Mono<List<String>> listFlux = animalFlux.collectList(); // робить те саме, що буфер без значення, но результатом є моно

        Mono<Map<Character,String>> listFlux = animalFlux.collectMap(t -> t.charAt(0));

        listFlux.subscribe(System.out::println);
    }

    @Test
    public void testAllAny(){
        Flux<Integer> flux = Flux.just(2,4,6,8,10,12,14);

        Mono<Boolean> mono = flux.all(i -> i % 2 == 0); // all повертає true якщо всі значення повертають правду

        mono.subscribe(System.out::println);

        Flux<Integer> flux1 = Flux.just(1,4,5,7,9,11);

        Mono<Boolean> mono1 = flux1.any(i -> i % 2 == 0); // any повертає true якщо хоча б одне значення повертає правду

        mono1.subscribe(System.out::println);
    }
}
