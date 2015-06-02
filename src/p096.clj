(ns p096)

(defn- to-int [c] (Character/digit c 10))

(def sudoku
  (with-open [rdr (clojure.java.io/reader "data/sudoku.txt")]
    (->> (line-seq rdr)
         (partition 10)
         (map rest)
         (mapv (fn [ns] (mapv #(mapv to-int %) ns)))
         (doall))))
