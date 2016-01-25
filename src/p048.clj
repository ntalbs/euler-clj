(ns p048)

(defn solve []
  (->> (range 1 (inc 1000))
       (map biginteger)
       (map #(.pow % %))
       (apply +')
       (#(mod % 10000000000))))
