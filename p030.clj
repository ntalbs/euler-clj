;; #030
;; Find the sum of all the numbers that can be written 
;; as the sum of fifth powers of their digits.

(use '[util :only (digits pow)])

(defn sum-of-5th-power-of-digits [n]
  (->> (digits n)
       (map (fn [x] (pow x 5)))
       (apply +)))

(defn p030 []
  (->> (range 2 1000000)
       (filter #(= % (sum-of-5th-power-of-digits %)))
       (apply +)))

(time (println (p030)))
