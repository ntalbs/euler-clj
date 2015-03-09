;; sum of the even-valued terms of fibonacci sequence <= 4,000,000

(ns p002)

(def limit 4000000)

;; using recurssion and memoization
(defn fibo-rec [n]
  (cond (= 1 n) 1
        (= 2 n) 1
        :else (+' (fibo-rec (- n 1)) (fibo-rec (- n 2)))))

(def fibo-rec (memoize fibo-rec))

(defn using-memoization []
  (->> (iterate inc 1)
       (map fibo-rec)
       (filter even?)
       (take-while #(<= % limit))
       (reduce +)))

;; using iteration
(def fibo-iter
  (->> (iterate (fn [[a b]] [b (+ a b)]) [1 1])
       (map first)))

(defn using-iteration []
  (->> fibo-iter
       (filter even?)
       (take-while #(<= % limit))
       (apply +)))

(defn solve []
  (time (println (using-memoization)))
  (time (println (using-iteration))))
