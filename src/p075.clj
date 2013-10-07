;; #075

(use '[util :only [gcd]])

(def l-max 1500000)
(def limit (int (Math/sqrt l-max)))

(defn p075 []
  (->> (for [m (range 1 (inc limit)) n (range 1 m)
             :when (odd? (- m n))
             :when (= 1 (gcd m n))]
         (sort [(- (* m m) (* n n)) (* 2 m n) (+ (* m m) (* n n))]))
       (mapcat (fn [[a b c]]
                 (->> (iterate inc 1)
                      (map (fn [k] [(* k a) (* k b) (* k c)]))
                      (take-while (fn [[a b c]] (<= (+ a b c) l-max))))))
       (filter (fn [[a b c]] (<= (+ a b c) l-max)))
       (group-by (fn [[a b c]] (+ a b c)))
       (filter (fn [[_ list]] (= 1 (count list))))
       count))

(time (println (p075)))
