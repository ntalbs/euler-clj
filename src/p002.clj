;; #002
;; sum of the even-valued terms of fibonacci sequence <= 4,000,000

(defn fibo [[a b]] [b (+ a b)])

(defn p002 []
  (let [limit 4000000]
    (->> (iterate fibo [1 1])
         (take-while (fn [[a b]] (<= b limit)))
         (map last)
         (filter even?)
         (apply +))))

(time (println (p002)))
