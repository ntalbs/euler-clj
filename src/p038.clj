(ns p038)

(defn- pandigital? [numstr]
  (= "123456789" (apply str (sort numstr))))

(defn- prod-concat [n]
  (loop [i 1 acc ""]
    (if (or (< 9 i) (<= 9 (count acc)))
      (apply str acc)
      (recur (inc i) (concat acc (str (* n i)))))))


(defn solve []
  (->> (range 9999 1 -1)
       (map prod-concat)
       (drop-while #(not (pandigital? %)))
       first))
