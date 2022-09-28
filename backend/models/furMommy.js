module.exports = (sequelize, DataTypes) => {
    const FurMommies = sequelize.define("FurMommies", {

    NID: DataTypes.STRING,
    });
  
    FurMommies.associate = (models) => {
      //One to Many association with User model
      //(Each user verification belongs to a User)
      FurMommies.belongsTo(models.Users, {
        foreignKey: {
          name: "userUNID",
        },
      });
    };
  
    return FurMommies;
  };
  