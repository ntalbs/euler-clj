;; sum of all multiples of 3 and 5 below 1000.

(ns p001)

(defn- solve1
  "brute force"
  [n]
  (->> (range n)
       (filter (fn [n] (or (= 0 (mod n 3)) (= 0 (mod n 5)))))
       (apply +)))

(defn- s
  ([n] (/ (* n (+ n 1)) 2))
  ([n m] (* m (s (quot n m)))))

(defn- solve2
  "using formula, s(n) = n(n+1)/2"
  [n]
  (let [n (dec n)]
    (-> (s n 3)
        (+ (s n 5))
        (- (s n 15)))))

(defn solve []
  (solve2 1000))
