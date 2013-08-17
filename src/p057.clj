;; #057

(def a (+ 1 (/ 1 2)))

(defn conv [a]
  (->> (+ 1 a) (/ 1) (+ 1)))

(defn digit-cnt [n]
  (count (str n)))

(defn p057 []
  (->> (iterate conv a)
       (take 1000)
       (filter (fn [r] (> (digit-cnt (numerator r)) (digit-cnt (denominator r)))))
       count))

(time (println (p057)))
