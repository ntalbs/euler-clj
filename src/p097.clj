;; Find the last ten digits of 28433×2^7830457+1.

(ns p097)

(defn solve []
  (-> (.modPow (biginteger 2) (biginteger 7830457) (biginteger 10000000000))
      (.multiply (biginteger 28433))
      (.add (biginteger 1))
      (.mod (biginteger 10000000000))))
