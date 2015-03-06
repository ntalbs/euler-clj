;; #001
;; sum of all multiples of 3 and 5 below 1000.
(ns p001)

; initial: brute force
; sufficiently fast when n is small, however, it will get slower when n get bigger.
(defn- using-brute-force [n]
  (->> (range n)
       (filter (fn [n] (or (= 0 (mod n 3)) (= 0 (mod n 5)))))
       (apply +)))


; improved: using formula, s(n) = n(n+1)/2
; much faster method. The size of n does not affect the calculation time.
(defn- s
  ([n] (/ (* n (+ n 1)) 2))
  ([n m] (* m (s (quot n m)))))

(defn- using-forumla [n]
  (let [n (dec n)]
    (-> (s n 3)
        (+ (s n 5))
        (- (s n 15)))))

(defn solve []
  (time (println "brute force => " (using-brute-force 1000)))
  (time (println "using formula => " (using-forumla 1000))))
