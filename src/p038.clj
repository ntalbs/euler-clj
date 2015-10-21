(ns p038)

(defn- pandigital? [numstr]
  (= "123456789" (apply str (sort numstr))))

(defn- prod-concat [n]
  (loop [i 1 acc ""]
    (if (< (count acc) 9)
      (recur (inc i) (str acc (* n i)))
      acc)))

(defn solve []
  (->> (range 9999 1 -1)
       (map prod-concat)
       (drop-while #(not (pandigital? %)))
       first))
