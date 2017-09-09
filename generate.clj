(ns example (:require [clojure.data.json :as json]))
(defn factorial [x]
    (defn _factorial [x] (if (zero? x) 1 (-> x dec _factorial (* x))))
    (trampoline _factorial x))
(def chars-0-to-9 (->> 10 range (map str) (map first) seq))
(defn fill-nil [x] (replace {nil 0} x))
(defn factorial_digits [x]
    (-> x
        bigint
        factorial
        str
        frequencies
        (map chars-0-to-9)
        fill-nil
        vec
    ))
(def digit_matrix (->> 8001 range (map factorial_digits) vec json/write-str))
(spit "frequencies.json" digit_matrix)
