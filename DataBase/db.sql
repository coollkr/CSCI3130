Create Table ga_db.User_Signup_and_Login (
  `user_id` int NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(10) NOT NULL,
  PRIMARY KEY (user_id));
    
Create Table ga_db.Workspace_Type (
  `workspace_type_id` INT NOT NULL,
  `workspace_type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (Workspace_type_id));  
  
Create Table ga_db.Workspace (
  `workspace_id` INT NOT NULL,
  `workspace_name` VARCHAR(50) NOT NULL,
  `workspace_type` INT NOT NULL,
  PRIMARY KEY (workspace_id),
  FOREIGN KEY (workspace_type)
  REFERENCES ga_db.Workspace_Type (Workspace_type_id));

Create Table ga_db.Lists (
  `list_id` INT NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  `workspace_id` INT NOT NULL,
  PRIMARY KEY (list_id),
  FOREIGN KEY (workspace_id)
  REFERENCES ga_db.Workspace (workspace_id));
  
Create Table ga_db.Cards (
  `card_id` INT NOT NULL,
  `title` VARCHAR(200) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `time` DATETIME NOT NULL,
  `list_id` INT NOT NULL,
  PRIMARY KEY (card_id),
  FOREIGN KEY (list_id)
  REFERENCES ga_db.Lists (list_id));

Create Table ga_db.Card_Users ( 
  `card_user_id` INT NOT NULL,
  `card_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (card_user_id),
  FOREIGN KEY (user_id)
  REFERENCES ga_db.User_Signup_and_Login (user_id),
  FOREIGN KEY (card_id)
  REFERENCES ga_db.Cards (card_id));
  
Create Table ga_db.Workspace_users (
  `workspace_user_id` INT NOT NULL,
  `workspace_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (workspace_user_id),
  FOREIGN KEY (workspace_id)
  REFERENCES ga_db.Workspace (workspace_id),
  FOREIGN KEY (user_id)
  REFERENCES ga_db.User_Signup_and_Login (user_id));
  
Create Table ga_db.Boards (
  `board_id` INT NOT NULL,
  `board_name` VARCHAR(50) NOT NULL,
  `board_description` VARCHAR(500) NOT NULL,
  `workspace_id` INT NOT NULL,
  PRIMARY KEY (board_id),
  FOREIGN KEY (workspace_id)
  REFERENCES ga_db.workspace (workspace_id));
 
Create Table ga_db.Board_Users (
  `board_user_id` INT NOT NULL,
  `board_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (board_user_id),
  FOREIGN KEY (user_id)
  REFERENCES ga_db.User_Signup_and_Login (user_id),
  FOREIGN KEY (board_id)
  REFERENCES ga_db.Boards (board_id));  


