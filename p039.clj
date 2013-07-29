;; #039
;; Refer to http://en.wikipedia.org/wiki/Pythagorean_triple

(use '[util :only (gcd)])

(defn triple
  "Returns a pythagorian triple for given m, n with m > n."
  [m n k]
  [(* k (- (* m m) (* n n)))
   (* k 2 m n)
   (* k (+ (* m m) (* n n)))])

(defn p039 [bound]
  (->> (for [m (range 1 (int (Math/sqrt bound)))
             n (range 1 m)
             k (range 1 (int (/ 1000 12)))
             :when (odd? (- m n))
             :when (= 1 (gcd m n))]
         (triple m n k))
       (filter (fn [[a b c]] (<= (+ a b c) bound)))
       (group-by (fn [[a b c]] (+ a b c)))
       (apply max-key (fn [[p v]] (count v)))))

(time (println (p039 1000)))
