;; What is the first term in the Fibonacci sequence to contain 1000 digits?

(ns p025)

(defn fibo [[a b]] [b (+' a b)])

(defn solve []
  (->> (iterate fibo [1 1])
       (map-indexed (fn [i e] [(inc i) e]))
       (drop-while (fn [[_ [a b]]] (< (count (str a)) 1000)))
       ffirst))
