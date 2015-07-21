;; What is the first term in the Fibonacci sequence to contain 1000 digits?

(ns p025)

(def fibo-iter
  (->> (iterate (fn [[a b]] [b (+' a b)]) [1 1])
       (map first)))

(defn solve []
  (->> fibo-iter
       (map-indexed (fn [i e] [(inc i) e]))
       (drop-while (fn [[_ a]] (< (count (str a)) 1000)))
       ffirst))
