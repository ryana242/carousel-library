
module.exports = (sequelize, DataTypes) => {
    const Users = sequelize.define("Users", { 
  
      // Unique UserID for all user
      userUNID: {
        type: DataTypes.STRING, 
        primaryKey: true,
      },
  
      name: DataTypes.STRING,
      email: DataTypes.STRING,
      dateOfBirth: DataTypes.DATE,
      contactNumber: DataTypes.STRING,
      password: DataTypes.STRING,
      actorType: {
        type: DataTypes.ENUM("Fur Baby", "Fur Mommy"),
      },
      
    });
  
    Users.associate = (models) => {
      
      //One to Many association with User verification model
      //(A user can have multiple user verification)
      Users.hasOne(models.FurMommies, {
        foreignKey: {
          name: "userUNID",
        },
      });
  
      
    };
  
    return Users;
  };
  