-- Seed minimal tasks at startup.
MERGE INTO task (id, title, description, status, priority, createdat) KEY(id) VALUES
  (1, 'Configurer H2', 'Verifier la console H2 est accessible', 'TODO', 'HIGH', TIMESTAMP '2026-11-04 09:00:00'),
  (2, 'Ecrire tests', 'Completer les tests du service Task', 'IN_PROGRESS', 'MEDIUM', TIMESTAMP '2026-11-04 10:00:00'),
  (3, 'Livrer API', 'Publier la premiere version de l''API', 'DONE', 'LOW', TIMESTAMP '2026-11-04 11:00:00');


