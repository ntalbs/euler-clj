(ns p064)

(defn expand-continued-fraction [n]
  (let [a0 (int (Math/sqrt n))]
    (loop [m 0, d 1, a a0, acc [a0]]
      (if (= a (* 2 a0))
        acc
        (let [m (- (* d a) m), d (/ (- n (* m m)) d), a (int (/ (+ a0 m) d))]
          (recur m d a (conj acc a)))))))

(defn square [x] (* x x))

(defn solve []
  (->> (range 2 (inc 10000))
       (filter #(not= % (square (int (Math/sqrt %)))))
       (map expand-continued-fraction)
       (filter #(even? (count %)))
       count))
