#!/usr/bin/env bb

;; SPDX-FileCopyrightText: 2025 Wong Yi Xiong <https://github.com/TheRealMintd>
;;
;; SPDX-License-Identifier: MPL-2.0

(require '[clojure.string :as str]
         '[babashka.fs :as fs])

(import [java.nio.file FileAlreadyExistsException])

(def to-kill "-RAF_DxO_DeepPRIMEXD")

(defn replacements
  "Get all the replacements that are required for this set of file names"
  [names]
  (->> names
       (map (fn [name] {:target name :to (str/replace name to-kill "")}))
       (filter #(not (= (get % :target) (get % :to))))))

(run! #(try (fs/move (get % :target) (get % :to))
            (catch FileAlreadyExistsException _ ())) (replacements *command-line-args*))
