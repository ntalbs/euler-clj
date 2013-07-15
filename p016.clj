;; #016
;; sum of digits of 2^1000

(use '[util :only [digits]])

(defn p016 []
  (->> (.pow (java.math.BigInteger. "2") 1000)
       digits
       (apply +)))

(time (println (p016)))
