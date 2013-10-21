;; #073

(use '[util :only (gcd)])

(defn reduced-proper-fractions [limit]
  (for [d (range 3 (inc limit)) n (range (int (/ d 3)) (inc (int (/ d 2))))
        :when (< 1/3 (/ n d) 1/2)
        :when (= 1 (gcd n d))]
    (/ n d)))

(defn p073 []
  (count (reduced-proper-fractions 12000)))

(time (println (p073)))