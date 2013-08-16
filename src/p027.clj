;; #027

(use '[util :only (prime?)])

(defn f [a b n]
  (+ (* n n) (* a n) b))

(defn prime-count [a b]
  (->> (iterate inc 0)
       (map #(f a b %))
       (map #(Math/abs %))
       (take-while prime?)
       count))

(defn seq-of-prime-count []
  (let [lower -999 upper 1000]
    (for [a (range  lower upper) b (range lower upper)
          :when (prime? b)
          :when (prime? (+ 1 a b))]
      {:a a :b b :ab (* a b) :cnt (prime-count a b)})))

; takes over 17 secs. need to improve.
(defn p027 []
  (reduce (fn [a b] (if (< (:cnt a) (:cnt b)) b a)) (seq-of-prime-count)))

(time (println (p027)))
