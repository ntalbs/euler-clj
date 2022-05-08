(ns main)

(defn- show-usage []
  (println "Usage: lein run [num]"))

(defn -main[& args]
  (println args)
  (println (count args))
  (let [n (->> args
               first
               Integer/parseInt
               (format "p%03d"))]
    (println ">>> resolved namespace: " n)
    (println (symbol n))
    (use '[(symbol n) :only solve])

;    (time (solve))
    ))
