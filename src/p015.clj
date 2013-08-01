;; #015
;; number of routes through 20x20 grid

(use '[util :only [factorial]])

(def f factorial)

(defn p015 []
  (let [f40 (f 40) f20 (f 20)]
    (/ f40 (* f20 f20))))

(time (println (p015)))
