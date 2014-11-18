;; Continued fraction expansion
;; http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion

(ns p064)

(defn expand-continued-fraction [n]
  (let [a0 (int (Math/sqrt n))]
    (loop [m 0, d 1, a a0, acc [a0]]
      (if (= a (* 2 a0))
        acc
        (let [m (- (* d a) m), d (/ (- n (* m m)) d), a (int (/ (+ a0 m) d))]
          (recur m d a (conj acc a)))))))

(defn sqr [x] (* x x))

(defn p064 []
  (->> (range 2 (inc 10000))
       (filter #(not= % (sqr (int (Math/sqrt %)))))
       (map expand-continued-fraction)
       (filter #(even? (count %)))
       count))

(defn solve []
  (time (p064)))
