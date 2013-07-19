;; #025
;; What is the first term in the Fibonacci sequence to contain 1000 digits?

(import java.math.BigInteger)

(defn fibo [[a b]] [b (+' a b)])

(defn p025 []
  (->> (iterate fibo [1 1])
       (map-indexed (fn [i e] [(inc i) e]))
       (drop-while (fn [[_ [a b]]] (< (count (str a)) 1000)))
       first
       first))

(time (println (p025)))
