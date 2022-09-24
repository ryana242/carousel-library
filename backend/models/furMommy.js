module.exports = (sequelize, DataTypes) => {
    const FurMommy = sequelize.define("FurMommy", {

    NID: DataTypes.STRING,
    });
  
    FurMommy.associate = (models) => {
      //One to Many association with User model
      //(Each user verification belongs to a User)
      FurMommy.belongsTo(models.Users, {
        foreignKey: {
          name: "userUNID",
        },
      });
    };
  
    return FurMommy;
  };
  