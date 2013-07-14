;; #016
;; sum of digits of 2^1000

(defn digits [n]
  (letfn [(digits-acc [n acc]
            (if (< n 10) (conj acc n)
                (recur (quot n 10) (conj acc (mod n 10)))))]
    (digits-acc n '())))

(defn p016 []
  (->> (.pow (java.math.BigInteger. "2") 1000)
       digits
       (apply +)))

(time (println (p016)))
