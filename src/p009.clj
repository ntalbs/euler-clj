(ns p009)

;; brute force: fairly fast when a+b+c is small, but not usable when a+b+c is large.
(defn using-brute-force []
  (first (for [a (range 1 1000)
               b (range a 1000)
               c [(- 1000 a b)]
               :when (= (+ (* a a) (* b b)) (* c c))]
           (* a b c))))


;; using pythagorean triplets:
;; http://en.wikipedia.org/wiki/Pythagorean_triple#Parent.2Fchild_relationships

(defn next-triplets [[a b c]]
  [(sort [(+ a (* -2 b) (* 2 c))
          (+ (* 2 a) (- b) (* 2 c))
          (+ (* 2 a) (* -2 b) (* 3 c))])
   (sort [(+ a (* 2 b) (* 2 c))
          (+ (* 2 a) b (* 2 c))
          (+ (* 2 a) (* 2 b) (* 3 c))])
   (sort [(+ (- a) (* 2 b) (* 2 c))
          (+ (* -2 a) b (* 2 c))
          (+ (* -2 a) (* 2 b) (* 3 c))])])

(defn primitive-triplets
  "Returns lazy sequence of primitive pythagorean triplets"
  ([ts] (let [more (for [t ts, next-t (next-triplets t)] next-t)]
          (lazy-cat ts (primitive-triplets more))))
  ([] (primitive-triplets ['(3 4 5)])))

(defn side-sum-is-not [sum]
  (fn [[a b c]] (not= sum (+ a b c))))

(defn using-formula []
  (->> (primitive-triplets)
       (mapcat (fn [[a b c]] (->> (for [k (range 100)] [(* k a) (* k b) (* k c)])
                                  (drop-while (side-sum-is-not 1000)))))
       (drop-while (side-sum-is-not 1000))
       (first)
       (apply *)))

(defn solve []
  (using-formula))
