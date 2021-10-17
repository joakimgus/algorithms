package org.pg4200.ex08;

import org.pg4200.les08.stream.MyStreamSupport;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AnotherStreamSupport {

    public static <T, C extends Iterable<T>> AnotherStream<T> createStream(C collection){
        return new Pipeline<T, T, T>(collection.iterator());
    }

    protected static class Pipeline<IN, OUT, T> implements AnotherStream<OUT> {

        private final Iterator<T> iterator;
        private final Pipeline<?, IN, ?> previousStage;

        public Pipeline(Iterator<T> iterator){
            this.iterator = iterator;
            this.previousStage = null;
        }

        public Pipeline(Pipeline section){
            this.iterator = section.iterator;
            this.previousStage = section;
        }

        @Override
        public int count() {
            int[] count = {0};

            Consumer<OUT> collectingConsumer = new Consumer<OUT>() {
                @Override
                public void accept(OUT out) {
                    count[0]++;
                }
            };

            return 0;
        }

      /*  private Consumer<T> chain = chainAllConsumersInThePipeline(Consumer<OUT> consumer) {
            Objects.requireNonNull(consumer);

            Pipeline p = null;

        }

       */

        @Override
        public String joinToString(String separator) {
            return null;
        }

        @Override
        public boolean any(Predicate<OUT> predicate) {
            return false;
        }

        @Override
        public Optional<OUT> reduce(BinaryOperator<OUT> accumulator) {
            return Optional.empty();
        }

        @Override
        public AnotherStream<OUT> distinct() {
            return null;
        }

        @Override
        public AnotherStream<OUT> skip(int n) {
            return null;
        }

        @Override
        public AnotherStream<OUT> sorted() {
            return null;
        }
    }
}
