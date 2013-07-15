(ns util)

(defn factorial
  "Returns the factorial of n"
  [n]
  (->> (range 1 (inc n))
       (map #(java.math.BigInteger. (str %)))
       (apply *)))

(defn digits
  "Retruns the list of digits of n"
  [n]
  (letfn [(digits-acc [n acc]
            (if (< n 10) (conj acc n)
                (recur (quot n 10) (conj acc (mod n 10)))))]
    (digits-acc n '())))
