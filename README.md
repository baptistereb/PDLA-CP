# PDLA-CP

## Run the project
Initialize the SQL structure by using the "create_table.sql" script.

## Structure SQL
- users (<u>user_id</u>, pseudo, password, user_type {user, volunteer, moderator})
- missions (<u>mission_id</u>, description, #user_id, mission_state {waiting, valid, realized}, motif, type {help, need_help})
- connection (<u>connection_id</u>, #mission_id, #user_id, connection_type {help, need_help})