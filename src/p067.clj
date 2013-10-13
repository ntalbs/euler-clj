;; #067
;; Basically the same as #018.

(use '[util :only [parse-int]]
     '[clojure.string :only [split]])

(def triangle
  (map (fn [s] (map parse-int (split s #" ")))
       (split (slurp "data/triangle.txt") #"\r\n")))

(defn find-max [t]
  (reduce (fn [ls vs] (map max (map + ls vs) (map + (rest ls) vs))) t))

(defn p067 []
  (first (find-max (reverse triangle))))

(time (println (p067)))
