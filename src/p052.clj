(ns p052)

(defn all-has-same-digits? [v]
  (->> v
       (map (comp sort str))
       (apply =)))

(defn solve []
  (->> (iterate inc 1)
       (map #(for [i [1 2 3 4 5 6]] (* i %)))
       (filter #(all-has-same-digits? %))
       ffirst))
