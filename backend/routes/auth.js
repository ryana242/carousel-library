const express = require("express");
const router = express.Router();
const { Op } = require("sequelize");
const { Users } = require("../models");
const uuid = require("uuid");
const bcrypt = require("bcrypt");
const { FurMommies } = require("../models");
//login signup


// Register as furbaby or furmommy
router.post("/register", async (req, res) => {
    if (!req.body.email) {
        return res.json({
            data: "",
            error: "Field cant be empty"
        })
    }
    console.log(req.body.email);
    const op = await Users.findOne({
        where: {
            email: req.body.email
        }
    })
    if (op) {
        res.json({
            data: "",
            error: "Email already registered!!!"
        });
    } else {
        let actorType
        if (req.body.userType == "Fur Baby") {
            actorType = Users.getAttributes().actorType.values[0];
        } else {
            actorType = Users.getAttributes().actorType.values[1];
        }
        const UNID = uuid.v4();
        const password = await bcrypt.hash(req.body.password, 10);
        //For furBabies
        await Users.create({
            userUNID: UNID,
            name: req.body.name,
            email: req.body.email,
            dateOfBirth: new Date(req.body.dateOfBirth),
            contactNumber: req.body.contactNumber,
            password: password,
            actorType: actorType,
        })

        //For furmommies
        await FurMommies.create ({
            userUNID: UNID,
            NID: req.body.NID
        })

        res.json({
            data: "Registration Successfull",
            error: "",
        });
    }
})

module.exports = router;