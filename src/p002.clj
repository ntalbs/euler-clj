;; #002
;; sum of the even-valued terms of fibonacci sequence <= 4,000,000

(ns p002)

(def fibo
  (->> (iterate (fn [[a b]] [b (+ a b)]) [1 1])
       (map first)))

(defn p002 [limit]
  (->> fibo
       (filter even?)
       (take-while #(<= % limit))
       (apply +)))

(defn solve []
  (time (println (p002 4000000))))
